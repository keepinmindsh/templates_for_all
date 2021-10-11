using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace sample_socket_server
{
    class SocketServer
    {
        public static Socket Server, Client;

        public static byte[] getByte = new byte[1024];
        public static byte[] setByte = new byte[1024];

        public const int sPort = 777;

        [STAThread]
        static void Main(string[] args)
        {
            string stringbyte = null;
            IPAddress serverIP = IPAddress.Parse("127.0.0.1");
            IPEndPoint serverEndPoint = new IPEndPoint(serverIP, sPort);

            try
            {
                Server = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                Server.Bind(serverEndPoint);
                Server.Listen(10);

                Console.WriteLine("-----------------------------------------------------");
                Console.WriteLine(" 클라이언트의 연결을 기다립니다........ ");
                Console.WriteLine("-----------------------------------------------------");

                Client = Server.Accept();

                if (Client.Connected)
                {
                    while (true)
                    {
                        Client.Receive(getByte, 0, getByte.Length, SocketFlags.None);
                        stringbyte = Encoding.UTF7.GetString(getByte);

                        if (stringbyte != String.Empty)
                        {
                            int getValueLength = 0;
                            getValueLength = byteArrayDefrag(getByte);
                            stringbyte = Encoding.UTF7.GetString(getByte, 0, getValueLength + 1);
                            Console.WriteLine("수신데이터:{0} | 길이:{1}", stringbyte, getValueLength + 1);
                            setByte = Encoding.UTF7.GetBytes(stringbyte);
                            Client.Send(setByte, 0, setByte.Length, SocketFlags.None);
                        }

                        getByte = new byte[1024];
                        setByte = new byte[1024];
                    }
                }
            }
            catch (System.Net.Sockets.SocketException socketEx)
            {
                Console.WriteLine("[Error]:{0}", socketEx.Message);
            }
            catch (System.Exception commonEx)
            {
                Console.WriteLine("[Error]:{0}", commonEx.Message);
            }
            finally
            {
                Server.Close();
                Client.Close();
            }
        }

        public static int byteArrayDefrag(byte[] sData)
        {
            int endLength = 0;

            for (int i = 0; i < sData.Length; i++)
            {
                if ((byte)sData[i] != (byte)0)
                {
                    endLength = i;
                }
            }

            return endLength;
        }
    }
}
