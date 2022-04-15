### Compiler Version
```
gcc version 9.4.0 (Ubuntu 9.4.0-1ubuntu1~20.04.1)
```
### Heap out-of-bounds
```
有問題的程式碼
```
```
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
```
```
ASan report
```
```
==573==ERROR: AddressSanitizer: heap-buffer-overflow on address 0x602000000015 at pc 0x562b9a65c29f bp 0x7ffee220b030 sp 0x7ffee220b020
WRITE of size 1 at 0x602000000015 thread T0
    #0 0x562b9a65c29e in main (/mnt/d/software_test/lab6/t1_s+0x129e)
    #1 0x7faa7afc40b2 in __libc_start_main (/lib/x86_64-linux-gnu/libc.so.6+0x240b2)
    #2 0x562b9a65c18d in _start (/mnt/d/software_test/lab6/t1_s+0x118d)

0x602000000015 is located 0 bytes to the right of 5-byte region [0x602000000010,0x602000000015)
allocated by thread T0 here:
    #0 0x7faa7b29f808 in __interceptor_malloc ../../../../src/libsanitizer/asan/asan_malloc_linux.cc:144
    #1 0x562b9a65c25e in main (/mnt/d/software_test/lab6/t1_s+0x125e)
    #2 0x7faa7afc40b2 in __libc_start_main (/lib/x86_64-linux-gnu/libc.so.6+0x240b2)

SUMMARY: AddressSanitizer: heap-buffer-overflow (/mnt/d/software_test/lab6/t1_s+0x129e) in main
Shadow bytes around the buggy address:
  0x0c047fff7fb0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0c047fff7fc0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0c047fff7fd0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0c047fff7fe0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0c047fff7ff0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
=>0x0c047fff8000: fa fa[05]fa fa fa fa fa fa fa fa fa fa fa fa fa
  0x0c047fff8010: fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa
  0x0c047fff8020: fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa
  0x0c047fff8030: fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa
  0x0c047fff8040: fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa
  0x0c047fff8050: fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa
Shadow byte legend (one shadow byte represents 8 application bytes):
  Addressable:           00
  Partially addressable: 01 02 03 04 05 06 07
  Heap left redzone:       fa
  Freed heap region:       fd
  Stack left redzone:      f1
  Stack mid redzone:       f2
  Stack right redzone:     f3
  Stack after return:      f5
  Stack use after scope:   f8
  Global redzone:          f9
  Global init order:       f6
  Poisoned by user:        f7
  Container overflow:      fc
  Array cookie:            ac
  Intra object redzone:    bb
  ASan internal:           fe
  Left alloca redzone:     ca
  Right alloca redzone:    cb
  Shadow gap:              cc
==573==ABORTING
```
```
valgrind report
```
```
==572== Memcheck, a memory error detector
==572== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==572== Using Valgrind-3.15.0 and LibVEX; rerun with -h for copyright info
==572== Command: ./t1_v
==572==
==572== Invalid write of size 1
==572==    at 0x1091AB: main (in /mnt/d/software_test/lab6/t1_v)
==572==  Address 0x4a48045 is 0 bytes after a block of size 5 alloc'd
==572==    at 0x483B7F3: malloc (in /usr/lib/x86_64-linux-gnu/valgrind/vgpreload_memcheck-amd64-linux.so)
==572==    by 0x10919E: main (in /mnt/d/software_test/lab6/t1_v)
==572==
==572== Invalid read of size 1
==572==    at 0x1091B6: main (in /mnt/d/software_test/lab6/t1_v)
==572==  Address 0x4a48045 is 0 bytes after a block of size 5 alloc'd
==572==    at 0x483B7F3: malloc (in /usr/lib/x86_64-linux-gnu/valgrind/vgpreload_memcheck-amd64-linux.so)
==572==    by 0x10919E: main (in /mnt/d/software_test/lab6/t1_v)
==572==
a
==572==
==572== HEAP SUMMARY:
==572==     in use at exit: 0 bytes in 0 blocks
==572==   total heap usage: 2 allocs, 2 frees, 1,029 bytes allocated
==572==
==572== All heap blocks were freed -- no leaks are possible
==572==
==572== For lists of detected and suppressed errors, rerun with: -s
==572== ERROR SUMMARY: 2 errors from 2 contexts (suppressed: 0 from 0)
```
```
ASan 能 , valgrind 能
```


### Stack out-of-bounds
```
有問題的程式碼
```
```
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main()
{
    int a[10] = {0};
    int b[10] = {0};
    a[10] = 0xcafe;
    
    return 0;
}
```
```
ASan report
```
```
==606==ERROR: AddressSanitizer: stack-buffer-overflow on address 0x7ffd8a1b63f8 at pc 0x55c0057b3399 bp 0x7ffd8a1b63a0 sp 0x7ffd8a1b6390
WRITE of size 4 at 0x7ffd8a1b63f8 thread T0
    #0 0x55c0057b3398 in main (/mnt/d/software_test/lab6/t2_s+0x1398)
    #1 0x7f2da4ee60b2 in __libc_start_main (/lib/x86_64-linux-gnu/libc.so.6+0x240b2)
    #2 0x55c0057b312d in _start (/mnt/d/software_test/lab6/t2_s+0x112d)

Address 0x7ffd8a1b63f8 is located in stack of thread T0 at offset 72 in frame
    #0 0x55c0057b31f8 in main (/mnt/d/software_test/lab6/t2_s+0x11f8)

  This frame has 2 object(s):
    [32, 72) 'a' (line 7) <== Memory access at offset 72 overflows this variable
    [112, 152) 'b' (line 8)
HINT: this may be a false positive if your program uses some custom stack unwind mechanism, swapcontext or vfork
      (longjmp and C++ exceptions *are* supported)
SUMMARY: AddressSanitizer: stack-buffer-overflow (/mnt/d/software_test/lab6/t2_s+0x1398) in main
Shadow bytes around the buggy address:
  0x10003142ec20: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x10003142ec30: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x10003142ec40: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x10003142ec50: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x10003142ec60: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
=>0x10003142ec70: 00 00 00 00 00 00 f1 f1 f1 f1 00 00 00 00 00[f2]
  0x10003142ec80: f2 f2 f2 f2 00 00 00 00 00 f3 f3 f3 f3 f3 00 00
  0x10003142ec90: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x10003142eca0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x10003142ecb0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x10003142ecc0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
Shadow byte legend (one shadow byte represents 8 application bytes):
  Addressable:           00
  Partially addressable: 01 02 03 04 05 06 07
  Heap left redzone:       fa
  Freed heap region:       fd
  Stack left redzone:      f1
  Stack mid redzone:       f2
  Stack right redzone:     f3
  Stack after return:      f5
  Stack use after scope:   f8
  Global redzone:          f9
  Global init order:       f6
  Poisoned by user:        f7
  Container overflow:      fc
  Array cookie:            ac
  Intra object redzone:    bb
  ASan internal:           fe
  Left alloca redzone:     ca
  Right alloca redzone:    cb
  Shadow gap:              cc
==606==ABORTING
```
```
valgrind report
```
```
==608== Memcheck, a memory error detector
==608== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==608== Using Valgrind-3.15.0 and LibVEX; rerun with -h for copyright info
==608== Command: ./t2_v
==608==
==608==
==608== HEAP SUMMARY:
==608==     in use at exit: 0 bytes in 0 blocks
==608==   total heap usage: 0 allocs, 0 frees, 0 bytes allocated
==608==
==608== All heap blocks were freed -- no leaks are possible
==608==
==608== For lists of detected and suppressed errors, rerun with: -s
==608== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)
```
ASan 能 , valgrind 能

### Global out-of-bounds 
```
有問題的程式碼
```
```
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int global_array[100] = {-1};
int main(int argc, char **argv) {
  return global_array[argc + 100];  
}
```
```
ASan report
```
```
==621==ERROR: AddressSanitizer: global-buffer-overflow on address 0x55faa2d071b4 at pc 0x55faa2d0422b bp 0x7ffc9ecd7420 sp 0x7ffc9ecd7410
READ of size 4 at 0x55faa2d071b4 thread T0
    #0 0x55faa2d0422a in main (/mnt/d/software_test/lab6/t3_s+0x122a)
    #1 0x7f6536b5b0b2 in __libc_start_main (/lib/x86_64-linux-gnu/libc.so.6+0x240b2)
    #2 0x55faa2d0410d in _start (/mnt/d/software_test/lab6/t3_s+0x110d)

0x55faa2d071b4 is located 4 bytes to the right of global variable 'global_array' defined in 'test3.c:5:5' (0x55faa2d07020) of size 400
SUMMARY: AddressSanitizer: global-buffer-overflow (/mnt/d/software_test/lab6/t3_s+0x122a) in main
Shadow bytes around the buggy address:
  0x0abfd4598de0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0abfd4598df0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0abfd4598e00: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0abfd4598e10: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0abfd4598e20: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
=>0x0abfd4598e30: 00 00 00 00 00 00[f9]f9 f9 f9 f9 f9 00 00 00 00
  0x0abfd4598e40: f9 f9 f9 f9 f9 f9 f9 f9 00 00 00 00 00 00 00 00
  0x0abfd4598e50: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0abfd4598e60: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0abfd4598e70: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0abfd4598e80: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
Shadow byte legend (one shadow byte represents 8 application bytes):
  Addressable:           00
  Partially addressable: 01 02 03 04 05 06 07
  Heap left redzone:       fa
  Freed heap region:       fd
  Stack left redzone:      f1
  Stack mid redzone:       f2
  Stack right redzone:     f3
  Stack after return:      f5
  Stack use after scope:   f8
  Global redzone:          f9
  Global init order:       f6
  Poisoned by user:        f7
  Container overflow:      fc
  Array cookie:            ac
  Intra object redzone:    bb
  ASan internal:           fe
  Left alloca redzone:     ca
  Right alloca redzone:    cb
  Shadow gap:              cc
==621==ABORTING
```
```
valgrind report
```
```
==633== Memcheck, a memory error detector
==633== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==633== Using Valgrind-3.15.0 and LibVEX; rerun with -h for copyright info
==633== Command: ./t3_v
==633==
==633==
==633== HEAP SUMMARY:
==633==     in use at exit: 0 bytes in 0 blocks
==633==   total heap usage: 0 allocs, 0 frees, 0 bytes allocated
==633==
==633== All heap blocks were freed -- no leaks are possible
==633==
==633== For lists of detected and suppressed errors, rerun with: -s
==633== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 0 from 0)
```
ASan 能 , valgrind 不能

### Use-after-free
```
有問題的程式碼
```
```
#include <iostream>
using namespace std;

int main(int argc, char **argv) {
  int *array = new int[100];
  delete [] array;
  return array[argc];  
}
```
```
ASan report
```
```
==658==ERROR: AddressSanitizer: heap-use-after-free on address 0x614000000044 at pc 0x55650fb07309 bp 0x7ffe3826c980 sp 0x7ffe3826c970
READ of size 4 at 0x614000000044 thread T0
    #0 0x55650fb07308 in main (/mnt/d/software_test/lab6/t4_s+0x1308)
    #1 0x7fd99aa680b2 in __libc_start_main (/lib/x86_64-linux-gnu/libc.so.6+0x240b2)
    #2 0x55650fb071cd in _start (/mnt/d/software_test/lab6/t4_s+0x11cd)

0x614000000044 is located 4 bytes inside of 400-byte region [0x614000000040,0x6140000001d0)
freed by thread T0 here:
    #0 0x7fd99b0926ef in operator delete[](void*) ../../../../src/libsanitizer/asan/asan_new_delete.cc:168
    #1 0x55650fb072bc in main (/mnt/d/software_test/lab6/t4_s+0x12bc)
    #2 0x7fd99aa680b2 in __libc_start_main (/lib/x86_64-linux-gnu/libc.so.6+0x240b2)

previously allocated by thread T0 here:
    #0 0x7fd99b091787 in operator new[](unsigned long) ../../../../src/libsanitizer/asan/asan_new_delete.cc:107
    #1 0x55650fb072a5 in main (/mnt/d/software_test/lab6/t4_s+0x12a5)
    #2 0x7fd99aa680b2 in __libc_start_main (/lib/x86_64-linux-gnu/libc.so.6+0x240b2)

SUMMARY: AddressSanitizer: heap-use-after-free (/mnt/d/software_test/lab6/t4_s+0x1308) in main
Shadow bytes around the buggy address:
  0x0c287fff7fb0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0c287fff7fc0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0c287fff7fd0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0c287fff7fe0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0c287fff7ff0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
=>0x0c287fff8000: fa fa fa fa fa fa fa fa[fd]fd fd fd fd fd fd fd
  0x0c287fff8010: fd fd fd fd fd fd fd fd fd fd fd fd fd fd fd fd
  0x0c287fff8020: fd fd fd fd fd fd fd fd fd fd fd fd fd fd fd fd
  0x0c287fff8030: fd fd fd fd fd fd fd fd fd fd fa fa fa fa fa fa
  0x0c287fff8040: fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa
  0x0c287fff8050: fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa fa
Shadow byte legend (one shadow byte represents 8 application bytes):
  Addressable:           00
  Partially addressable: 01 02 03 04 05 06 07
  Heap left redzone:       fa
  Freed heap region:       fd
  Stack left redzone:      f1
  Stack mid redzone:       f2
  Stack right redzone:     f3
  Stack after return:      f5
  Stack use after scope:   f8
  Global redzone:          f9
  Global init order:       f6
  Poisoned by user:        f7
  Container overflow:      fc
  Array cookie:            ac
  Intra object redzone:    bb
  ASan internal:           fe
  Left alloca redzone:     ca
  Right alloca redzone:    cb
  Shadow gap:              cc
==658==ABORTING
```
```
valgrind report
```
```
==649== Memcheck, a memory error detector
==649== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==649== Using Valgrind-3.15.0 and LibVEX; rerun with -h for copyright info
==649== Command: ./t4_v
==649==
==649== Invalid read of size 4
==649==    at 0x1091F1: main (in /mnt/d/software_test/lab6/t4_v)
==649==  Address 0x4da7c84 is 4 bytes inside a block of size 400 free'd
==649==    at 0x483D74F: operator delete[](void*) (in /usr/lib/x86_64-linux-gnu/valgrind/vgpreload_memcheck-amd64-linux.so)
==649==    by 0x1091DC: main (in /mnt/d/software_test/lab6/t4_v)
==649==  Block was alloc'd at
==649==    at 0x483C583: operator new[](unsigned long) (in /usr/lib/x86_64-linux-gnu/valgrind/vgpreload_memcheck-amd64-linux.so)
==649==    by 0x1091C5: main (in /mnt/d/software_test/lab6/t4_v)
==649==
==649==
==649== HEAP SUMMARY:
==649==     in use at exit: 0 bytes in 0 blocks
==649==   total heap usage: 2 allocs, 2 frees, 73,104 bytes allocated
==649==
==649== All heap blocks were freed -- no leaks are possible
==649==
==649== For lists of detected and suppressed errors, rerun with: -s
==649== ERROR SUMMARY: 1 errors from 1 contexts (suppressed: 0 from 0)
```
ASan 能 , valgrind 能

### Use-after-return
```
有問題的程式碼
```
```
#include <iostream>
using namespace std;

int *ptr;
__attribute__((noinline))
void FunctionThatEscapesLocalObject() {
  int local[100];
  ptr = &local[0];
}

int main(int argc, char **argv) {
  FunctionThatEscapesLocalObject();
  return ptr[argc];
}
```
```
ASan report
```
```
==713==ERROR: AddressSanitizer: stack-use-after-return on address 0x7f1966a67034 at pc 0x5633f3111432 bp 0x7ffd6a3c7bf0 sp 0x7ffd6a3c7be0
READ of size 4 at 0x7f1966a67034 thread T0
    #0 0x5633f3111431 in main (/mnt/d/software_test/lab6/t5_s+0x1431)
    #1 0x7f1969e2e0b2 in __libc_start_main (/lib/x86_64-linux-gnu/libc.so.6+0x240b2)
    #2 0x5633f31111cd in _start (/mnt/d/software_test/lab6/t5_s+0x11cd)

Address 0x7f1966a67034 is located in stack of thread T0 at offset 52 in frame
    #0 0x5633f3111298 in FunctionThatEscapesLocalObject() (/mnt/d/software_test/lab6/t5_s+0x1298)

  This frame has 1 object(s):
    [48, 448) 'local' (line 7) <== Memory access at offset 52 is inside this variable
HINT: this may be a false positive if your program uses some custom stack unwind mechanism, swapcontext or vfork
      (longjmp and C++ exceptions *are* supported)
SUMMARY: AddressSanitizer: stack-use-after-return (/mnt/d/software_test/lab6/t5_s+0x1431) in main
Shadow bytes around the buggy address:
  0x0fe3acd44db0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0fe3acd44dc0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0fe3acd44dd0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0fe3acd44de0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0fe3acd44df0: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
=>0x0fe3acd44e00: f5 f5 f5 f5 f5 f5[f5]f5 f5 f5 f5 f5 f5 f5 f5 f5
  0x0fe3acd44e10: f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5
  0x0fe3acd44e20: f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5
  0x0fe3acd44e30: f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5 f5
  0x0fe3acd44e40: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
  0x0fe3acd44e50: 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
Shadow byte legend (one shadow byte represents 8 application bytes):
  Addressable:           00
  Partially addressable: 01 02 03 04 05 06 07
  Heap left redzone:       fa
  Freed heap region:       fd
  Stack left redzone:      f1
  Stack mid redzone:       f2
  Stack right redzone:     f3
  Stack after return:      f5
  Stack use after scope:   f8
  Global redzone:          f9
  Global init order:       f6
  Poisoned by user:        f7
  Container overflow:      fc
  Array cookie:            ac
  Intra object redzone:    bb
  ASan internal:           fe
  Left alloca redzone:     ca
  Right alloca redzone:    cb
  Shadow gap:              cc
==713==ABORTING
```
```
valgrind report
```
```
==714== Memcheck, a memory error detector
==714== Copyright (C) 2002-2017, and GNU GPL'd, by Julian Seward et al.
==714== Using Valgrind-3.15.0 and LibVEX; rerun with -h for copyright info
==714== Command: ./t5_v
==714==
==714== Invalid read of size 4
==714==    at 0x1091F8: main (in /mnt/d/software_test/lab6/t5_v)
==714==  Address 0x1ffeffff04 is on thread 1's stack
==714==  428 bytes below stack pointer
==714==
==714==
==714== HEAP SUMMARY:
==714==     in use at exit: 0 bytes in 0 blocks
==714==   total heap usage: 1 allocs, 1 frees, 72,704 bytes allocated
==714==
==714== All heap blocks were freed -- no leaks are possible
==714==
==714== For lists of detected and suppressed errors, rerun with: -s
==714== ERROR SUMMARY: 1 errors from 1 contexts (suppressed: 0 from 0)
```
ASan 能 , valgrind 能




