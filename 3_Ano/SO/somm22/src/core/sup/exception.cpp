/*
 * \brief the somm22 exception class
 * \author Artur Pereira - 2022
 */

#include <exception>

#include <stdio.h>
#include <string.h>

#include "exception.h"

namespace somm22
{

// ================================================================================== //

    /*
     * \brief somm22 exception data type
     */
    Exception::Exception(int _en, const char *_func) 
            : en(_en), func(_func)
    {
        sprintf(msg, "\e[01;31m%s: error %d (%s)\e[0m", func, en, strerror(en));
    }

// ================================================================================== //
    const char *Exception::what() const throw()
    {
        return msg;
    }

// ================================================================================== //

} // end of namespace somm22
