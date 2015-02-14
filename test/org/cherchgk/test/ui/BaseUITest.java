/*
 * Copyright 2012-2015 Andrey Grigorov, Anton Grigorov
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.cherchgk.test.ui;

import com.codeborne.selenide.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Базовый класс для UI-тестов.
 * Перед началом выполнения тестов выполняется запуск тестового сервера,
 * по завершению тестирования сервер выключается.
 *
 * @author Andrey Grigorov (peneksglazami@gmail.com)
 */
public abstract class BaseUITest {

    private static final int TEST_SERVER_PORT = 7777;
    private static TestWebAppLauncher launcher;

    @BeforeClass
    public static void startServer() throws Exception {
        Configuration.baseUrl = "http://localhost:" + TEST_SERVER_PORT;
        launcher = new TestWebAppLauncher(TEST_SERVER_PORT);
        launcher.run();
    }

    @AfterClass
    public static void stopServer() {
        if (launcher != null) {
            launcher.stop();
            launcher = null;
        }
    }
}