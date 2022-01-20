package com.dranoer.freenow.data.mapper

import com.dranoer.freenow.data.model.FleetType
import com.dranoer.freenow.data.model.VehicleEntity
import com.dranoer.freenow.data.model.VehicleModel

fun mapToEntity(remoteModel: VehicleModel): VehicleEntity {
    return VehicleEntity(
        id = remoteModel.id,
        fleetType = FleetType.mapFromName(remoteModel.fleetType),
        latitude = remoteModel.coordinate.latitude,
        longitude = remoteModel.coordinate.longitude,
    )
}