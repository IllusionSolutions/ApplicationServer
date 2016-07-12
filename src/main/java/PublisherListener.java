import io.moquette.interception.AbstractInterceptHandler;
import io.moquette.interception.messages.InterceptPublishMessage;
import persistence.PersistenceHandler.PersistenceHandler;

/**
 * Created by MothusiMasibi on 2016/07/05.
 */

public class PublisherListener extends AbstractInterceptHandler
{

    private PersistenceHandler persistence;

    public PublisherListener(PersistenceHandler persistence)
    {
        this.persistence = persistence;
    }

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