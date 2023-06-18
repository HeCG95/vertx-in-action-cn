package chapter5._03_reactivex.hotCold;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * 备注
 *
 * @author HeCG
 * @date 2023/6/18 21:13
 * @since 1.0
 */
public class Cold {

    public static void main(String[] args) throws Exception {

        @NonNull
        Flowable<Object> observeOn = Flowable.create(e -> {
            e.onNext(1); Thread.sleep(1000);
            e.onNext(2); Thread.sleep(1000);
            e.onNext(3); Thread.sleep(1000);
            e.onNext(4); Thread.sleep(1000);
            e.onNext(5); Thread.sleep(1000);
            e.onNext(6); Thread.sleep(1000);
        }, BackpressureStrategy.BUFFER).observeOn(Schedulers.computation());
        Thread.sleep(1000 * 1);
        observeOn.subscribe(e -> System.out.println(Thread.currentThread().getName() + " + next + " + e));
        Thread.sleep(1000 * 1);
        observeOn.subscribe(e -> System.out.println(Thread.currentThread().getName() + " + next + " + e));

    }

}
