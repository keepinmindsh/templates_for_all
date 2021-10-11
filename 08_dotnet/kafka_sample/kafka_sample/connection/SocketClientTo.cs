using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace Bongs.Module
{
    class SocketClientTo
    {
        public static Socket socket;
        public static byte[] getbyte = new byte[1024];
        public static byte[] setbyte = new byte[1024];

        public const int sPort = 777;
        

        public void connect()
        {
            string sendstring = null;
            string getstring = null;

            IPAddress serverIP = IPAddress.Parse("127.0.0.1");
            IPEndPoint serverEndPoint = new IPEndPoint(serverIP, sPort);

            socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            Console.WriteLine("-----------------------------------------------------");
            Console.WriteLine(" 서버로 접속을 시작합니다. [엔터를 입력하세요] ");
            Console.WriteLine("-----------------------------------------------------");
            Console.ReadLine();

            socket.Connect(serverEndPoint);

            if (socket.Connected)
            {
                Console.WriteLine(">> 정상적으로 연결 되었습니다.");
            }

            /*while (true)
            {
                Console.Write(">>");
                sendstring = Console.ReadLine();

                if (sendstring != String.Empty)
                {
                    int getValueLength = 0;
                    setbyte = Encoding.UTF7.GetBytes(sendstring);
                    socket.Send(setbyte, 0, setbyte.Length, SocketFlags.None);
                    Console.WriteLine("송신 데이터 : {0} | 길이{1}", sendstring, setbyte.Length);
                    socket.Receive(getbyte, 0, getbyte.Length, SocketFlags.None);
                    getValueLength = byteArrayDefrag(getbyte);
                    getstring = Encoding.UTF7.GetString(getbyte, 0, getValueLength + 1);
                    Console.WriteLine(">>수신된 데이터 :{0} | 길이{1}", getstring, getValueLength + 1);
                }

                getbyte = new byte[1024];
            }*/
        }

        public void sendMessage(String messages)
        {
            messages = Console.ReadLine();

            if (messages != String.Empty)
            {
                int getValueLength = 0;
                setbyte = Encoding.UTF7.GetBytes(messages);
                socket.Send(setbyte, 0, setbyte.Length, SocketFlags.None);
                Console.WriteLine("송신 데이터 : {0} | 길이{1}", messages, setbyte.Length);
                socket.Receive(getbyte, 0, getbyte.Length, SocketFlags.None);
                getValueLength = byteArrayDefrag(getbyte);
                messages = Encoding.UTF7.GetString(getbyte, 0, getValueLength + 1);
                Console.WriteLine(">>수신된 데이터 :{0} | 길이{1}", messages, getValueLength + 1);
            }

            getbyte = new byte[1024];
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
