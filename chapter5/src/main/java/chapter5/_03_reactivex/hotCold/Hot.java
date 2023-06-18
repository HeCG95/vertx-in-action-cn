package chapter5._03_reactivex.hotCold;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.schedulers.Schedulers;

/**
 * 备注
 *
 * @author HeCG
 * @date 2023/6/18 21:17
 * @since 1.0
 */
public class Hot {

    public static void main(String[] args) throws Exception {

        @NonNull
        ConnectableFlowable<Object> publish = Flowable.create(e -> {
            e.onNext(1); Thread.sleep(1000);System.out.println(1+ " " + Thread.currentThread().getName());
            e.onNext(2); Thread.sleep(1000);System.out.println(2+ " " + Thread.currentThread().getName());
            e.onNext(3); Thread.sleep(1000);System.out.println(3+ " " + Thread.currentThread().getName());
            e.onNext(4); Thread.sleep(1000);System.out.println(4+ " " + Thread.currentThread().getName());
            e.onNext(5); Thread.sleep(1000);System.out.println(5+ " " + Thread.currentThread().getName());
            e.onNext(6); Thread.sleep(1000);System.out.println(6+ " " + Thread.currentThread().getName());
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.computation()).publish();
        System.out.println("connect");
        publish.connect();
        System.out.println("subscribe 1");
        publish.subscribe(e -> System.out.println(Thread.currentThread().getName() + " + next + " + e));
        Thread.sleep(3000 * 1);
        System.out.println("subscribe 2");
        publish.observeOn(Schedulers.computation()).subscribe(e -> System.out.println(Thread.currentThread().getName() + " + next + " + e));

    }

}
