using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace sample_ui_socket_server
{
    public partial class Form1 : Form
    {

        ArrayList socklist = new ArrayList();
        public static string data = null;

        //클라이언트에게 오는 메시지를 받을 버퍼 선언
        private byte[] bytes = new byte[1024];

        public List<string> clientIP = new List<string>();
        
        public Form1()
        {
            InitializeComponent();
            FormClosing += new FormClosingEventHandler(MainForm_FormClosing);
        }

        public void MainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (socklist != null)
            {
                socklist = null;
            }
            Application.Exit();
        }

        public static string TruncateLeft(string value, int maxLength)
        {
            if (string.IsNullOrEmpty(value)) return value;
            return value.Length <= maxLength ? value : value.Substring(0, maxLength);
        }

        private void metroTile1_Click(object sender, EventArgs e)
        {
           
        }

        /// <summary>
        /// 클라에게 접속 연결 및 데이터 받는 함수
        /// </summary>
        public void SocketServer()
        {
            ClientIPStore();

            //클라이언트 IP를 알아서 해당 클라이언트에게 접속 요청
            new Thread(delegate ()
            {
                while (true)
                {
                    try
                    {
                        foreach (var client in clientIP)
                        {
                            // Create a TCP/IP Socket
                            Socket sock = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);

                            // 클라이언트의 IP와 포트번호를 이용해 접속 시도
                            //sock.Connect(new IPEndPoint(IPAddress.Parse(client.ToString()), 12000));
                            IPEndPoint endpoint = new IPEndPoint(IPAddress.Parse(client.ToString()), 777);

                            //각 클라이언트 Search 할 때 Timeout 걸어준다.
                            IAsyncResult result = sock.BeginConnect(IPAddress.Parse(client.ToString()), 777, null, null);
                            bool success = result.AsyncWaitHandle.WaitOne(500, true);

                            if (!success)
                            {
                                FailConnect(sock, client);
                            }
                            else
                            {
                                Invoke((MethodInvoker)delegate
                                {
                                    SuccessConnect(sock, client);
                                });
                            }
                        }
                    }
                    catch (SocketException se)
                    {
                        MessageBox.Show("해당 클라이언트가 없습니다.");
                    }
                    Invoke((MethodInvoker)delegate
                    {
                        accessClient.AppendText(Environment.NewLine);
                        failClient.AppendText(Environment.NewLine);
                    });
                    Thread.Sleep(5000);
                }
            }).Start();
        }

        private void SuccessConnect(Socket sock, string client)
        {
            //lbConnect.Items.Add(client.ToString() + "[IP의 Client 연결 성공].");
            accessClient.AppendText(client.ToString() + "[IP의 Client 연결 성공]." + Environment.NewLine);
            socklist.Clear();
            //현재 연결 되어있는 클라이언트들만 따로 ArrayList에 저장
            socklist.Add(sock);
            new Thread(delegate ()
            {
                while (true)
                {
                    try
                    {
                        data = null;
                        // Client에서 들어오는 연결을 처리한다.
                        while (true)
                        {
                            bytes = new byte[1024];
                            int bytesRec = sock.Receive(bytes);
                            data += Encoding.UTF8.GetString(bytes, 0, bytesRec);
                            Thread.Sleep(500);
                            if (data.IndexOf("<eof>") > -1)
                                break;
                        }

                        // Truncate the <eof>
                        data = TruncateLeft(data, data.Length - 5);

                        // Client에서 받은 메시지를 listbox로 보여줍니다.
                        Invoke((MethodInvoker)delegate
                        {
                            //lbRevMsg.Items.Add(string.Format("Text received : {0}", data));
                            messageFromClient.AppendText(string.Format(client.ToString() + "received : {0}", data) +
                                                   Environment.NewLine);
                        });

                        // 클라이언트에서 받은 메시지를 다시 클라이언트에게 에코해준다.
                        data = "[Server Echo 메시지]" + data;
                        byte[] msg = Encoding.UTF8.GetBytes(data);

                        //MessageBox.Show(data);

                        foreach (Socket socket in socklist)
                        {
                            socket.Send(msg);
                        }
                        //sock.Send(msg);
                    }
                    catch
                    {
                        //MessageBox.Show("Server : DISCONNECTION!");
                        sock.Close();
                        sock.Dispose();
                        break;
                    }
                }
            }).Start();
        }

        private void FailConnect(Socket sock, string client)
        {
            // NOTE, MUST CLOSE THE SOCKET
            sock.Close();
            Invoke((MethodInvoker)delegate
            {
                //lbDisconnect.Items.Add(client.ToString() + "[IP의 Client 연결 실패].");
                failClient.AppendText(client.ToString() + "[IP의 Client 연결 실패]." + Environment.NewLine);
            });
        }

        /// <summary>
        /// 서버가 관리하는 클라이언트들의 IP를 List를 이용하여 저장하고 있다.
        /// </summary>
        public void ClientIPStore()
        {
            clientIP.Add("127.0.0.1");
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {
            SocketServer();
        }
    }
}
