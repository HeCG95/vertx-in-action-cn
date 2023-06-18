package chapter5.reactivex;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * 备注
 *
 * @author HeCG
 * @date 2023/6/18 21:18
 * @since 1.0
 */
public class HotPublish {

    public static void main(String[] args) throws Exception {

        PublishSubject<Integer> publishSubject = PublishSubject.create();
        publishSubject.onNext(1);
        publishSubject.onNext(2);
        publishSubject.onNext(3);
        System.out.println("subscribe 1");
        publishSubject.observeOn(Schedulers.computation()).subscribe(e -> System.out.println(Thread.currentThread().getName() + " + next + " + e));
        publishSubject.onNext(4);
        publishSubject.onNext(5);
        System.out.println("subscribe 2");
        publishSubject.observeOn(Schedulers.computation()).subscribe(e -> System.out.println(Thread.currentThread().getName() + " + next + " + e));
        publishSubject.onNext(6);

    }

}
