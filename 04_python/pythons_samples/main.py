from lines_pubsub import pubsub_call


def print_hi(name):
    print(f'Hi, {name}')

    # 패키지 호출 및 정의 방법 ( github package 사용 방법 )
    pubsub_call(print_hi, "project_id", "topic_id", "subscription_id")


if __name__ == '__main__':
    print_hi('PyCharm')

