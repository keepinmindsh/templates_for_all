using Confluent.Kafka;
using System;
using System.Threading;
using Bongs.Module;
using Newtonsoft.Json;

namespace kafka_sample
{
    class Program
    {
        static void Main(string[] args)
        {
            var config = new ConsumerConfig
            {
                GroupId = "test-consumer-group", // 해당 코드는 설정 코드여야 한다. 
                //BootstrapServers = "172.18.25.168:9092", // 해당 코드는 설정 코드여야 한다. 
                BootstrapServers = "172.18.25.182:9092", // 해당 코드는 설정 코드여야 한다.
                AutoOffsetReset = AutoOffsetReset.Earliest
            };

            using (var consumer = new ConsumerBuilder<Ignore, string>(config).Build())
            {
                consumer.Subscribe("wingsent5-machine"); // 해당 코드는 설정 코드여야 한다. 

                CancellationTokenSource cts = new CancellationTokenSource();
                Console.CancelKeyPress += (_, e) =>
                {
                    e.Cancel = true;
                    cts.Cancel();
                };

                try
                {
                    Thread t1 = new Thread(() =>
                    {
                        while (true)
                        {
                            try
                            {
                                var consumerMessages = consumer.Consume(cts.Token);

                                Logger.SendInfoToText($"Consumed Message: '{consumerMessages.Value}' at: '{consumerMessages.TopicPartitionOffset}'.", "kafka_sample");
                                Console.WriteLine($"Consumed Message: '{consumerMessages.Value}' at: '{consumerMessages.TopicPartitionOffset}'.");

                                KafkaMessageVO kafkaMessageVO = JsonConvert.DeserializeObject<KafkaMessageVO>(consumerMessages.Value);

                                ProgramSwitcher.execute(new ParamBuilder()
                                                        .machineType(kafkaMessageVO.machineType)
                                                        .companyId(kafkaMessageVO.companyId)
                                                        .bsnsCode(kafkaMessageVO.bsnsCode)
                                                        .propertyNo(kafkaMessageVO.propertyNo)
                                                        .folioNo(kafkaMessageVO.folioNo)
                                                        .roomNo(kafkaMessageVO.roomNo)
                                                        .posNo(kafkaMessageVO.posNo)
                                                        .jobType(kafkaMessageVO.jobType)
                                                        );

                            }
                            catch (ConsumeException e)
                            {
                                Logger.SendErrorToText(e, "kafka_sample");
                            }
                        }
                    });

                    t1.Start();


                    Thread t2 = new Thread(() => { 

                    });

                    t2.Start();


                }
                catch (OperationCanceledException)
                {
                    consumer.Close();
                }

            }
        }
    }
}
