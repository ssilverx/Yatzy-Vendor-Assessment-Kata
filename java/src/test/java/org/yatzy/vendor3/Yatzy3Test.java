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

package org.yatzy.vendor3;

import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yatzy.RollInput;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Yatzy3Test {

    private Yatzy3 calculator;

    @BeforeEach
    void beforeEach() {
        this.calculator = new Yatzy3();
    }

    @Test
    public void returns_correct_score_for_given_roll_and_category() throws Exception {
        final String[] rollsAndCategories = this.readRollsAndCategories();
        CombinationApprovals.verifyAllCombinations(this::calculateScore, rollsAndCategories);
    }

    private String[] readRollsAndCategories() throws Exception {
        final List<String> rollsAndCategories = new ArrayList<>();
        try (final BufferedReader br = this.getBufferedReader()) {
            String line;
            while ((line = br.readLine()) != null) {
                rollsAndCategories.add(line);
            }
        }
        return rollsAndCategories.toArray(new String[] {});
    }

    private BufferedReader getBufferedReader() {
        final InputStream in = Objects.requireNonNull(
                Yatzy3Test.class.getClassLoader().getResourceAsStream("valid_rolls_and_categories.csv"));
        return new BufferedReader(new InputStreamReader(in));
    }

    private int calculateScore(String providedInput) {
        final RollInput roll = this.calculator.parseDiceAndCategory(providedInput);
        return this.calculator.score(roll.dice, roll.category);
    }
}
