namespace service {

struct ServiceRequest {
    char* data;
    int size;
};

struct ServiceResponse {
    char* data;
    int size;
};

void create();
void destroy();

void callService(ServiceRequest& req, ServiceResponse& res);
void processService();
}  // namespace service