from google.cloud import pubsub_v1


def pubsub_call(client_callback, project_id, topic_id, subscription_id):
    topic_name = 'projects/{project_id}/topics/{topic}'.format(
        project_id=project_id,
        topic=topic_id,  # Set this to something appropriate.
    )

    subscription_name = 'projects/{project_id}/subscriptions/{sub}'.format(
        project_id=project_id,
        sub=subscription_id,  # Set this to something appropriate.
    )

    def callback(message):
        client_callback(message.data)
        message.ack()

    with pubsub_v1.SubscriberClient() as subscriber:
        subscriber.create_subscription(
            name=subscription_name, topic=topic_name)
        future = subscriber.subscribe(subscription_name, callback)
