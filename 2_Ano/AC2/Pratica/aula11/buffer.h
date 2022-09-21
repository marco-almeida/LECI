typedef struct {
    char mem[100];  // Storage area
    int nchar;      // Number of characters to be transmitted
    int posrd;      // Position of the next character to be transmitted
} t_buf;
