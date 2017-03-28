package twoActorsFixed.actors;

import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.testkit.TestActorRef;
import org.junit.Test;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.Arrays;

import static org.junit.Assert.*;


public class WorkerActorTest {

    private ActorSystem system = ActorSystem.create("test");

    @Test
    public void testActorUnit(){

        final TestActorRef worker = TestActorRef.create(system, WorkerActor.props(), "testWorker");
        final WorkerActor workerActor = (WorkerActor) worker.underlyingActor();

        MasterActor.Work work = new MasterActor.Work(1, Arrays.asList(1, 2, 3));
        WorkerActor.WorkStatus expectedWorkStatus = new WorkerActor.WorkStatus(1, "DONE");

        assertEquals(expectedWorkStatus, workerActor.doWork(work));
    }

    @Test
    public void testActorIntegration() throws Exception {

        final TestActorRef worker = TestActorRef.create(system, WorkerActor.props(), "testWorker");
        Future<Object> future = Patterns.ask(worker, new MasterActor.Work(1, Arrays.asList(1, 2, 3)), 1000);

        assertTrue(future.isCompleted());
        assertEquals(new WorkerActor.WorkStatus(1, "DONE"), Await.result(future, Duration.Zero()));
    }

}