package com.aangles_cmestas_mquispeyn.laboratorio07

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var textViewA1 : TextView
    lateinit var textViewA2 : TextView
    lateinit var textViewA3 : TextView

    lateinit var textViewM1 : TextView
    lateinit var textViewM2 : TextView
    lateinit var textViewM3 : TextView

    lateinit var sensorManager : SensorManager
    lateinit var accelerometer : Sensor
    lateinit var magnetometer : Sensor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewA1 = findViewById(R.id.textViewA1)
        textViewA2 = findViewById(R.id.textViewA2)
        textViewA3 = findViewById(R.id.textViewA3)

        textViewM1 = findViewById(R.id.textViewM1)
        textViewM2 = findViewById(R.id.textViewM2)
        textViewM3 = findViewById(R.id.textViewM3)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if(accelerometer != null)
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        else{
            textViewA1.text = "Acelerómetro no soportado"
            textViewA2.text = ""
            textViewA3.text = ""
        }
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        if(magnetometer != null)
            sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL)
        else{
            textViewM1.text = "Mangetómetro no soportado"
            textViewM2.text = ""
            textViewM3.text = ""
        }
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        var sensor = p0?.sensor
        if (sensor != null) {
            if (sensor.type == Sensor.TYPE_ACCELEROMETER) {
                if (p0 != null) {
                    textViewA1.text = p0.values[0].toString()
                    textViewA2.text = p0.values[1].toString()
                    textViewA3.text = p0.values[2].toString()
                }
            }
            else if(sensor.type == Sensor.TYPE_MAGNETIC_FIELD){
                if (p0 != null) {
                    textViewM1.text = p0.values[0].toString()
                    textViewM2.text = p0.values[1].toString()
                    textViewM3.text = p0.values[2].toString()
                }
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}