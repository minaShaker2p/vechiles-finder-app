package com.rezkalla.remote.utils

import com.rezkalla.remote.model.Data
import com.rezkalla.remote.model.Stats
import com.rezkalla.remote.model.VehicleNetwork
import com.rezkalla.remote.model.VehiclesResponse

class TestDataGenerator {
    companion object {
        fun getFakeResponse(): VehiclesResponse {
            val vehiclesNetwork = listOf(
                VehicleNetwork(
                    "6348dfa0-1b20-40ed-98e9-fe9e232b6105",
                    "8ece0495-bef0-4eac-a58e-dede2bf975a3",
                    "868446031763952",
                    "BERLIN",
                    "CLAIMED",
                    "5VRiXTOvRWbWfAlIKDv10HrE8LJ2",
                    "2019-10-10T06:35:21.153Z",
                    91,
                    "ACTIVE",
                    "AB",
                    118160,
                    52.506731,
                    13.289618
                ),
                VehicleNetwork(
                    "9a59f908-d45f-4159-9843-a1e2a4b85731",
                    "4cd6d67a-56f4-42ec-ab0b-85c6a8834ea0",
                    "862061041542068",
                    "BERLIN",
                    "CLAIMED",
                    "Rd2CFMWkCzLPHiDZOWMlDeV9o783",
                    "2019-10-10T06:35:21.153Z",
                    91,
                    "LOW_BATTERY",
                    "AB",
                    118160,
                    52.506731,
                    13.289618
                )
            )
            val stats = Stats(38, 0, 113)
            return VehiclesResponse(Data(vehiclesNetwork, stats))
        }
    }
}
