using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bongs.Module
{
    static class ProgramSwitcher
    {

        public static void execute(ParamBuilder param)
        {
            switch (param.getMachine())
            {
                case "ROOM_MNG":

                    Console.WriteLine("RoomManagement");
                    
                    break;
                case "ROOM_KEY":

                    Console.WriteLine("RoomKey");

                    break;
                case "CREDIT_CARD":

                    Console.WriteLine("CreditCard");

                    break;
            }
        }
    }
}
