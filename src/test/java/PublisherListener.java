import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;

/**
 * Created by MothusiMasibi on 2016/07/05.
 */

class PublisherListener extends AbstractInterceptHandler
{
    @Override
    public void onPublish(InterceptPublishMessage message)
    {
        System.out.println(
                "\nClient " + message.getClientID() +
                 "\nTopic: " + message.getTopicName() +
                 "\nMessage: " + new String(message.getPayload().array())
        );

    }



}