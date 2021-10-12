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

                    // TODO - TCP Socket Client에 의한 프로그램 호출 

                    Console.WriteLine("RoomManagement");
                    
                    break;
                case "ROOM_KEY":

                    // TODO - TCP Socket Client에 의한 프로그램 호출 

                    Console.WriteLine("RoomKey");

                    break;
                case "CREDIT_CARD":

                    // TODO - TCP Socket Client에 의한 프로그램 호출 

                    Console.WriteLine("CreditCard");

                    break;
            }
        }
    }
}
