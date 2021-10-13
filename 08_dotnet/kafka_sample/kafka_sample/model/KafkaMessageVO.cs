using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bongs.Module
{
    class KafkaMessageVO
    {
        public string machineType { get; set; }
        public string companyId { get; set; }
        public string bsnsCode { get; set; }
        public string propertyNo { get; set; }
        public string folioNo { get; set; }
        public string roomNo { get; set; }
        public string posNo { get; set; }
        public string vendorId { get; set; }

        public string jobType { get; set; }

    }
}
