/*
 *  Copyright 2014-2016 CyberVision, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/*
 * @file kaa_base64.c
 *
 */


#include <stddef.h>
#include <stdint.h>
#include "kaa_mem.h"
#include "../kaa_common.h"
#include "kaa_base64.h"


static char decoding_table[] = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x3E, 0x00, 0x00, 0x00, 0x3F,
                                0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x3A, 0x3B,
                                0x3C, 0x3D, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06,
                                0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
                                0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16,
                                0x17, 0x18, 0x19, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x1A, 0x1B, 0x1C, 0x1D, 0x1E, 0x1F, 0x20,
                                0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28,
                                0x29, 0x2A, 0x2B, 0x2C, 0x2D, 0x2E, 0x2F, 0x30,
                                0x31, 0x32, 0x33, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};


kaa_error_t kaa_base64_decode(const char *encoded_data, size_t encoded_data_length, char *decoded_data, size_t *decoded_data_length)
{
    KAA_RETURN_IF_NIL3(encoded_data,decoded_data,decoded_data_length,KAA_ERR_BADPARAM);

    if (encoded_data_length % 4 != 0) return KAA_ERR_BADPARAM;

    size_t remainig_decoded_length = encoded_data_length / 4 * 3;
    if (remainig_decoded_length > *decoded_data_length) return KAA_ERR_BUFFER_IS_NOT_ENOUGH;

    *decoded_data_length = remainig_decoded_length;

    if (encoded_data[encoded_data_length - 1] == '=') (*decoded_data_length)--;
    if (encoded_data[encoded_data_length - 2] == '=') (*decoded_data_length)--;

    int i = 0, j = 0;
    while (i < encoded_data_length) {
        uint32_t sextet_a = encoded_data[i] == '=' ? 0 & i++ : decoding_table[(int)encoded_data[i++]];
        uint32_t sextet_b = encoded_data[i] == '=' ? 0 & i++ : decoding_table[(int)encoded_data[i++]];
        uint32_t sextet_c = encoded_data[i] == '=' ? 0 & i++ : decoding_table[(int)encoded_data[i++]];
        uint32_t sextet_d = encoded_data[i] == '=' ? 0 & i++ : decoding_table[(int)encoded_data[i++]];

        uint32_t triple = (sextet_a << 3 * 6)
        + (sextet_b << 2 * 6)
        + (sextet_c << 1 * 6)
        + (sextet_d << 0 * 6);

        if (j < *decoded_data_length) decoded_data[j++] = (triple >> 2 * 8) & 0xFF;
        if (j < *decoded_data_length) decoded_data[j++] = (triple >> 1 * 8) & 0xFF;
        if (j < *decoded_data_length) decoded_data[j++] = (triple >> 0 * 8) & 0xFF;
    }

    return KAA_ERR_NONE;
}
