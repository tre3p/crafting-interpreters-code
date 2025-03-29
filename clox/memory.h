#ifndef CLOX__MEMORY_H_
#define CLOX__MEMORY_H_

#include "common.h"

#define DEFAULT_CAPACITY 8

#define GROW_CAPACITY(capacity) \
  ((capacity) < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : (capacity) * 2)

#define GROW_ARRAY(type, pointer, old_count, new_count) \
  (type*) reallocate(pointer, sizeof(type) * (old_count), sizeof(type) * (new_count))

#define FREE_ARRAY(type, pointer, old_count) \
  reallocate(pointer, sizeof(type) * (old_count), 0)

void *reallocate(void *ptr, size_t old_size, size_t new_size);


#endif //CLOX__MEMORY_H_
