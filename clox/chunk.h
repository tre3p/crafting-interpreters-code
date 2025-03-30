#ifndef CLOX__CHUNK_H_
#define CLOX__CHUNK_H_

#include "common.h"
#include "value.h"

typedef enum {
  OP_CONSTANT,
  OP_NEGATE,
  OP_RETURN,
  OP_ADD,
  OP_SUBTRACT,
  OP_MULTIPLY,
  OP_DIVIDE,
} OpCode;

typedef struct {
  int count; /* actual amount of chunks   */
  int capacity; /* already allocated count */
  uint8_t *code;
  int *lines;
  ValueArray constants;
} Chunk;

void init_chunk(Chunk *chunk);
void write_chunk(Chunk *chunk, uint8_t byte, int line);
int add_constant(Chunk *chunk, Value value);
void free_chunk(Chunk * chunk);

#endif //CLOX__CHUNK_H_
