using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bongs.Module
{
    class ParamBuilder
    {

        private Param param;

        public ParamBuilder()
        {
            param = new Param("", "");
        }

        public ParamBuilder messages(String messages)
        {
            param.setMessages(messages);
            return this;
        }

        public ParamBuilder machine(String machine)
        {
            param.setMachine(machine);
            return this;
        }

        public String getMachine()
        {
            return param.getMachine();
        }

        public String getMessges()
        {
            return param.getMessages();
        }

    }
}
