package chapter5._03_reactivex.hotCold;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;

/**
 * 备注
 *
 * @author HeCG
 * @date 2023/6/18 21:15
 * @since 1.0
 */
public class ColdReplay {

    public static void main(String[] args) throws Exception {

        ReplaySubject<Integer> replaySubject = ReplaySubject.create(1);
        replaySubject.onNext(1);
        replaySubject.onNext(2);
        replaySubject.onNext(3);
        System.out.println("subscribe 1");

        replaySubject.observeOn(Schedulers.computation()).subscribe(e -> System.out.println(Thread.currentThread().getName() + " + next + " + e));
        replaySubject.onNext(4);
        replaySubject.onNext(5);
        System.out.println("subscribe 2");

        replaySubject.observeOn(Schedulers.computation()).subscribe(e -> System.out.println(Thread.currentThread().getName() + " + next + " + e));
        replaySubject.onNext(6);

    }

}
