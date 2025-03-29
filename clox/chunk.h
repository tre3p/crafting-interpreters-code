#ifndef CLOX__CHUNK_H_
#define CLOX__CHUNK_H_

#include "common.h"

typedef enum {
  OP_RETURN,
} OpCode;

typedef struct {
  int count; /* actual amount of chunks   */
  int capacity; /* already allocated count */
  uint8_t *code;
} Chunk;

void init_chunk(Chunk *chunk);
void write_chunk(Chunk *chunk, uint8_t byte);
void free_chunk(Chunk * chunk);

#endif //CLOX__CHUNK_H_
