#include <iostream>

void StackFunc(char* t1, char* t2)
{
    std::cout << t1[32];
    return;
}

int main(int argc, char const *argv[])
{
    char byte_text1[] = "1234567";
    char byte_text2[] = "0123456";
    StackFunc(byte_text1, byte_text2);
    return 0;
}
