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

package ru.art.gradle.constants

const val MODEL_PACKAGE = "model"
const val MAPPING_PACKAGE = "mapping"
const val GENERATOR_GROUP = "generator"
const val GENERATE_MAPPERS_TASK = "generateValueMappersByModels"
const val GENERATE_SOAP_ENTITIES_TASK = "generateSoapModelsAndMappers"
const val COMPILE_MODELS_TASK = "compileModels"
val DEFAULT_COMPILE_MODELS_SOURCES = mutableSetOf("model", "constants")
