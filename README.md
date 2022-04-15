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
```
### Stack out-of-bounds
```
有問題的程式碼
```
```
ASan report
```
```
valgrind report
```
ASan 能/不能 , valgrind 能/不能

