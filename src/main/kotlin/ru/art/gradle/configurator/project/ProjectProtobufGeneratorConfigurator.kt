/*
 * ART Java
 *
 * Copyright 2019 ART
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

package ru.art.gradle.configurator.project

import com.google.protobuf.gradle.*
import org.gradle.api.*
import org.gradle.api.tasks.*
import org.gradle.kotlin.dsl.*
import ru.art.gradle.constants.*
import ru.art.gradle.context.Context.projectExtension
import ru.art.gradle.logging.*
import ru.art.gradle.provider.*
import java.io.File.*

fun Project.configureProtobufGenerator() {
    protobuf {
        protoc(closureOf<ExecutableLocator> {
            generatedFilesBaseDir = "${projectDir.absolutePath}$separator$PROTO_DIRECTORY"
            artifact = PROTOBUF_COMPILER_ARTIFACT(projectExtension().externalDependencyVersionsConfiguration.protobufCompilerVersion)
        })
    }

    afterEvaluate {
        val compileJavaTaskDependsOn = compileJavaTask().dependsOn
        if (!projectExtension().protobufGeneratorConfiguration.compileJavaDependsOnExtractIncludeProtoTask) {
            compileJavaTask().setDependsOn(compileJavaTaskDependsOn.filter { task -> task as? String ?: (task as? Task)?.name ?: (task as? TaskProvider<*>)?.name != extractIncludeProtoTask().name ?: true })
        }
        if (!projectExtension().protobufGeneratorConfiguration.compileJavaDependsOnExtractProtoTask) {
            compileJavaTask().setDependsOn(compileJavaTaskDependsOn.filter { task -> task as? String ?: (task as? Task)?.name ?: (task as? TaskProvider<*>)?.name != extractProtoTask().name ?: true })
        }
        if (!projectExtension().protobufGeneratorConfiguration.compileJavaDependsOnGenerateProtoTask) {
            compileJavaTask().setDependsOn(compileJavaTaskDependsOn.filter { task -> task as? String ?: (task as? Task)?.name ?: (task as? TaskProvider<*>)?.name != generateProtoTask().name ?: true })
        }
        additionalAttention("Disable 'extractIncludeProtoTask' task before 'compileJava' task")
        additionalAttention("Disable 'extractProtoTask' task before 'compileJava' task")
        additionalAttention("Disable 'generateProtoTask' task before 'compileJava' task")
    }
}