//
// Created by Milica on 3.6.2024..
//

#include "petric_bogdan_onlineshop_JNIexample.h"

extern "C"{JNIEXPORT jint JNICALL Java_petric_bogdan_onlineshop_JNIexample_smanjiCenu
  (JNIEnv * env, jobject jobj, jint cena){
  return double(cena) * 0.8;
  }
}
