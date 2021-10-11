using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bongs.Module
{
    static class ProgramSwitcher
    {
        static SockerClient socketClient = new SockerClient("127.0.0.1", 777);

        public static void execute(ParamBuilder param)
        {
            switch (param.getMachine())
            {
                case "Hello World":

                    Console.WriteLine("Consumed Message: Hello World has been called!!");
                    socketClient.acceptClient(param.getMessges());
                    break;
                case "1112":
                    socketClient = new SockerClient("127.0.0.1", 778);
                    socketClient.acceptClient(param.getMessges());
                    break;
                case "1113":
                    socketClient = new SockerClient("127.0.0.1", 779);
                    socketClient.acceptClient(param.getMessges());
                    break;
            }
        }
    }
}
