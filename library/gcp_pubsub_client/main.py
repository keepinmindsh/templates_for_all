from lines_pubsub import pubsub_client


def callback_func():
    print("Hello World")


if __name__ == '__main__':
    pubsub_client.pubsub_call(
        callback_func(),
        "dev1",
        "topic1",
        "sub"
    )


