#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_io_dushu_room_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "你好 田子游！";
    return env->NewStringUTF(hello.c_str());
}
