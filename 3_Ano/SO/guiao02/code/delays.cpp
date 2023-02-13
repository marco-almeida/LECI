/*
 * \brief Generation of delays
 *
 * \author (2016) Artur Pereira <artur at ua.pt>
 */

#include "delays.h"

#include <stdlib.h>
#include <stdint.h>
#include <math.h>
#include <unistd.h>

/* A busy waiting delay */
void bwDelay(uint32_t n)
{
    double b = 0.0;
    double c = 0.0;
    /* generate time delay */
    for (uint32_t i = 0; i < n; i++)
    {
        b = cos(c + M_PI / 4);
        c = sqrt(fabs(b));
    }
}

/* A busy waiting random delay */
void bwRandomDelay(uint32_t n1, uint32_t n2)
{
    static bool firstTime = true;
    if (firstTime) {
        srandom(getpid());
        firstTime = false;
    }

    /* generate a random value in interval */
    uint32_t n = floor(((double)rand() / (RAND_MAX + 1.0)) * (n2-n1+1)) + n1;

    /* generate time delay */
    bwDelay(n);
}

/* A gaussian delay, with given mean and standard deviation */
#define NSUM 12
void gaussianDelay(double mean, double std)
{
    /* generate random gaussian number with mean 0 and std 1 */
	double x = 0;
	for (int i = 0; i < NSUM; i++)
		x += (double)rand() / RAND_MAX;

	x -= NSUM / 2.0;
	//x /= sqrt(NSUM / 12.0);
    
    /* adjust to required mean and std */
    x += mean;
    if (x < 0.0) x = 0.0;
    x *= std;

	/* generate de delay */
    usleep((unsigned int)(round(x * 1000)));
}
