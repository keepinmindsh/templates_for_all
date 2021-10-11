using Confluent.Kafka;
using System;
using System.Threading;
using Bongs.Module;

namespace kafka_sample
{
    class Program
    {
        static void Main(string[] args)
        {
            var config = new ConsumerConfig
            {
                GroupId = "test-consumer-group",
                BootstrapServers = "localhost:9092",
                AutoOffsetReset = AutoOffsetReset.Earliest
            };

            using (var consumer = new ConsumerBuilder<Ignore, string>(config).Build())
            {
                consumer.Subscribe("bong-lines");

                CancellationTokenSource cts = new CancellationTokenSource();
                Console.CancelKeyPress += (_, e) =>
                {
                    e.Cancel = true;
                    cts.Cancel();
                };

                try
                {
                    while (true)
                    {
                        try
                        {
                            var cr = consumer.Consume(cts.Token);
                            Console.WriteLine($"Consumed Message: '{cr.Value}' at: '{cr.TopicPartitionOffset}'.");


                            ProgramSwitcher.execute(new ParamBuilder()
                                                    .machine(cr.Value)
                                                    .messages("test"));

                        }
                        catch(ConsumeException e)
                        {
                            Logger.SendErrorToText(e, "kafka_sample");
                        }
                    }
                }
                catch (OperationCanceledException)
                {
                    consumer.Close();
                }

            }
        }
    }
}
