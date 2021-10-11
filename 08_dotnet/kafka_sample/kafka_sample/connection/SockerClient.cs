using System;
using System.Net;
using System.Net.Sockets;
using System.Text;

namespace Bongs.Module
{
    class SockerClient
    {
        Socket sck;

        private String ip;
        private int port;

        public SockerClient(String ip, int port)
        {
            this.port = port;
            this.ip = ip;
        }
        

        public void acceptClient(String str)
        {
            sck = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            IPEndPoint localEndPoint = new IPEndPoint(IPAddress.Parse(ip), port);
            try
            {
                sck.Connect(localEndPoint);
                string text = str;
                byte[] data = Encoding.ASCII.GetBytes(text);

                sck.Send(data);
            }
            catch(Exception ex)
            {
                Logger.SendErrorToText(ex, "SockerClient" );
            }
        }
    }
}
