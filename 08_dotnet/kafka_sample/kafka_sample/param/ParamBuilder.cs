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
            param = new Param();
        }


        public ParamBuilder machineType(String machineType)
        {
            param.machineType = machineType;
            return this;
        }

        public ParamBuilder companyId(String companyId)
        {
            param.companyId = companyId;
            return this;
        }

        public ParamBuilder bsnsCode(String bsnsCode)
        {
            param.bsnsCode = bsnsCode;
            return this;
        }

        public ParamBuilder propertyNo(String propertyNo)
        {
            param.propertyNo = propertyNo;
            return this;
        }

        public ParamBuilder folioNo(String folioNo)
        {
            param.folioNo = folioNo;
            return this;
        }

        public ParamBuilder roomNo(String roomNo)
        {
            param.roomNo = roomNo;
            return this;
        }

        public ParamBuilder posNo(String posNo)
        {
            param.posNo = posNo;
            return this;
        }

        public ParamBuilder vendorId(String vendorId)
        {
            param.vendorId = vendorId;
            return this;
        }

        public ParamBuilder jobType(String jobType)
        {
            param.jobType = jobType;
            return this;
        }


        public Param getParam()
        {
            return param;
        }

    }
}
