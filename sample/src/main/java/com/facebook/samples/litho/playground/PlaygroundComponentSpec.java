/*
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.facebook.samples.litho.playground;

import android.graphics.Color;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.Size;
import com.facebook.litho.SizeSpec;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayoutWithSizeSpec;
import com.facebook.litho.widget.Text;

import static com.facebook.litho.SizeSpec.UNSPECIFIED;

@LayoutSpec
public class PlaygroundComponentSpec {

  @OnCreateLayoutWithSizeSpec
  static Component onCreateLayoutWithSizeSpec(ComponentContext c, int widthSpec, int heightSpec) {
    final Component textComponent =
        Text.create(c).textSizeSp(16).maxLines(1).text("Some text to measure asdfasfdasdfajskldfa;sdfjasdlfkasjdklfa;sldfajskdfajsd;falskjdfjlasdfaskjdfasdfasdfasdfasd").flexGrow(1.0f).backgroundColor(Color.GREEN).build();

    Column.Builder column = Column.create(c).child(textComponent);

    // UNSPECIFIED sizeSpecs will measure the text as being one line only,
    // having unlimited width.
    final Size textOutputSize = new Size();
    textComponent.measure(
        c,
        SizeSpec.makeSizeSpec(0, UNSPECIFIED),
        SizeSpec.makeSizeSpec(0, UNSPECIFIED),
        textOutputSize);

    // Assuming SizeSpec.getMode(widthSpec) == EXACTLY or AT_MOST.
    final int layoutWidth = SizeSpec.getSize(widthSpec);
    final boolean textFits = (textOutputSize.width <= layoutWidth);
    if (!textFits) {
      final Component textComponent2 =
          Text.create(c).textSizeSp(16).textColor(Color.WHITE).text("Click me to expand text").backgroundColor(Color.BLUE).build();
      column.child(textComponent2);
    }

    return column.build();
  }
}
