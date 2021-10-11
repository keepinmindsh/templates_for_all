using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bongs.Module
{
    class Param
    {
        private String machine;
        private String messages;
        
        public Param(String machine, String messages)
        {
            this.machine = machine;
            this.messages = messages;
        }

        public void setMachine(String machine)
        {
            this.machine = machine;
        }

        public void setMessages(String messages)
        {
            this.messages = messages;
        }

        public String getMachine()
        {
            return machine;
        }

        public String getMessages()
        {
            return messages;
        }
    }
}
