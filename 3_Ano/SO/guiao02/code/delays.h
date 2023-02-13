/**
 * @file
 *
 * \brief Generation of delays
 *
 * \author (2016,2022) Artur Pereira <artur at ua.pt>
 */
#ifndef __SO_IPC_INCREMENTER_DELAYS_
#define __SO_IPC_INCREMENTER_DELAYS_

#include <stdint.h>

/**
 * \brief Generate a busy waiting delay
 * \param n number of delay units
 */
void bwDelay(uint32_t n);

/**
 * \brief Generate a busy waiting delay random delay in interval [n1,n2]
 * \param n number of delay units
 */
void bwRandomDelay(uint32_t n1, uint32_t n2);

/**
 * \brief Generate a delay based on a gaussian distribution
 * \param mean mean time in milliseconds
 * \param std standard deviation in milliseconds
 */
void gaussianDelay(double mean, double std);

#endif /* __SO_IPC_INCREMENTER_DELAY_ */
