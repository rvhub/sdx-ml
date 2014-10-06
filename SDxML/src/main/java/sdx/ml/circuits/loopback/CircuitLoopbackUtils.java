/**
 *
 */
package sdx.ml.circuits.loopback;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sdx.ml.phedex.pojos.PhedexCircuit;

/**
 * @author ramiro
 *
 */
public class CircuitLoopbackUtils {

    private final static Logger logger = LoggerFactory.getLogger(CircuitLoopbackUtils.class);

	private static final ScheduledExecutorService loopbackExecutor = Executors.newScheduledThreadPool(16, new ThreadFactory() {
		final AtomicLong SEQ = new AtomicLong(0L);
		@Override
		public Thread newThread(Runnable r) {
			final Thread t = new Thread(r, "LoopBackExecutor :- " + SEQ.incrementAndGet());
			t.setDaemon(true);
			return t;
		}
	});

	public static ScheduledFuture<?> changeStatus(final PhedexCircuit circuit, final PhedexCircuit.Status newStatus, long delay, TimeUnit unit) {
		return loopbackExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				logger.info("Changing status {} for circuit: {}", newStatus, circuit);
				circuit.setStatus(newStatus);
			}
		}, delay, unit);
	}

}

