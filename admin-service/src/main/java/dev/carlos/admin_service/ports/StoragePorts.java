package dev.carlos.admin_service.ports;

public interface StoragePorts {
    String uploadFile(byte[] fileData, String filename, String contentType);
}
