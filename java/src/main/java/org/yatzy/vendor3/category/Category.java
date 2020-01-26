/*
 * Copyright (C) Fortumo OÜ, 2020
 * All rights reserved. Proprietary and confidential.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * All rights reserved. Proprietary and confidential.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Distribution, modification, reproduction, merging, publishing, sub-licensing,
 * sale and/or any other use (in binary form or otherwise) of this software is
 * not permitted, unless Fortumo OÜ has explicitly and in writing permitted such
 * use of the software.
 *
 * THIS SOFTWARE IS PROVIDED BY COPYRIGHT HOLDER ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Fortumo OÜ BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.yatzy.vendor3.category;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Category {

    private final List<Integer> dice;

    Category(List<Integer> dice) {
        this.dice = dice;
    }

    public static int nofakind(int n, List<Integer> dice) {
        final Map<Integer, Integer> frequencies = frequencies(dice);
        for (int i : Arrays.asList(5,4,3,2,1)) {
            if (frequencies.get(i) >= n) {
                return i*n;
            }
        }
        return 0;
    }

    public abstract int calculateScore();

    int sum(List<Integer> dice) {
        return dice.stream().mapToInt(Integer::intValue).sum();
    }

    List<Integer> getDice() {
        return this.dice;
    }

    static Map<Integer, Integer> frequencies(List<Integer> dice) {
        final HashMap<Integer, Integer> frequencies = new HashMap<>();
        for (int i : Arrays.asList(6, 5, 4, 3, 2, 1)) {
            frequencies.put(i, 0);
        }
        for (int die : dice) {
            frequencies.put(die, frequencies.get(die) + 1);
        }

        return frequencies;
    }
}
