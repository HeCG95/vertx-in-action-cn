#include <jni.h>
#include <stdio.h>
#include "HelloBuffer.h"

// Implementation of native method sayHello() of HelloJNI class
JNIEXPORT void JNICALL Java_HelloBuffer_initc(JNIEnv *, jclass, jobject) {
   int *iBuf = (*env)->GetDirectBufferAddress(env, jobject);
    iBuf[0] = -2;
    iBuf[1] = -1;
    iBuf[2] = 0;
    iBuf[3] = 1;
    iBuf[4] = 2;
}
