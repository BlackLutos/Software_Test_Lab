#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int global_array[100] = {-1};
int main(int argc, char **argv) {
  return global_array[argc + 100];  // BOOM
}