#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main()
{
    char *str = malloc(5);
    str[5] = 'a';
    printf("%c\n", str[5]);
    free(str);
    
    return 0;
}