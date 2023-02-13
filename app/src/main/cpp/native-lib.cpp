// Created 2023 - Baltazarus

#include <jni.h>
#include <string>
#include <vector>
#include <iostream>
#include <ctime>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_myrandomitempicker_MainActivity_getRandomItem(
        JNIEnv *env, jobject thiz, jobjectArray str_arr
)
{
    std::vector<std::string> vec_items;
    jsize arl = env->GetArrayLength(str_arr);
    if(arl > 0)
    {
        for(jsize i = 0; i < arl; ++i)
        {
            jboolean iscpy;
            jstring tjs = (jstring)env->GetObjectArrayElement(str_arr, i);
            std::string tjstds = env->GetStringUTFChars(tjs, &iscpy);
            vec_items.push_back(tjstds);
        }
    }
    return env->NewStringUTF(vec_items[rand() % arl].c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_myrandomitempicker_MainActivity_initializeRand(JNIEnv *env, jobject thiz)
{
    srand((unsigned int)time(nullptr));
    return;
}