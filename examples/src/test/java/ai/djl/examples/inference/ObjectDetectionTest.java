/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ai.djl.examples.inference;

import ai.djl.ModelException;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.translate.TranslateException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ObjectDetectionTest {

    @Test
    public void testObjectDetection() throws ModelException, TranslateException, IOException {
        DetectedObjects result = ObjectDetection.predict();
        Assert.assertEquals(result.getNumberOfObjects(), 3);
        List<String> objects = Arrays.asList("dog", "bicycle", "car");
        for (Classifications.Classification obj : result.items()) {
            Assert.assertTrue(objects.contains(obj.getClassName()));
            Assert.assertTrue(Double.compare(obj.getProbability(), 0.9) > 0);
        }
    }
}
