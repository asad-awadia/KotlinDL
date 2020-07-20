package examples.experimental.hdf5.lenetconfig

data class SequentialConfig(
    val backend: String? = "tensorflow",
    val class_name: String? = "Sequential",
    val config: Config?,
    val keras_version: String? = "2.2.4-tf"
)