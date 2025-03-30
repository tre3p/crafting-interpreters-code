#ifndef CLOX__VM_H_
#define CLOX__VM_H_

#include "chunk.h"
#include "value.h"

#define STACK_MAX 256

typedef struct {
  Chunk *chunk;
  uint8_t *ip;
  Value stack[STACK_MAX];
  Value *stack_top;
} VM;

typedef enum {
  INTERPRET_OK,
  INTERPRET_COMPILE_ERROR,
  INTERPRET_RUNTIME_ERROR
} InterpretResult;

void init_vm();
InterpretResult interpret(Chunk *chunk);
void free_vm();
void push(Value value);
Value pop();

#endif //CLOX__VM_H_
