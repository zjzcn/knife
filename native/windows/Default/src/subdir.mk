################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
C:/workspace/knife/native/src/check.c \
C:/workspace/knife/native/src/class.c \
C:/workspace/knife/native/src/field.c \
C:/workspace/knife/native/src/reference.c \
C:/workspace/knife/native/src/trace.c \
C:/workspace/knife/native/src/util.c 

OBJS += \
./src/check.o \
./src/class.o \
./src/field.o \
./src/reference.o \
./src/trace.o \
./src/util.o 

C_DEPS += \
./src/check.d \
./src/class.d \
./src/field.d \
./src/reference.d \
./src/trace.d \
./src/util.d 


# Each subdirectory must supply rules for building sources it contributes
src/check.o: C:/workspace/knife/native/src/check.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -I"C:\workspace\knife\native\include\windows" -O2 -g -Wall -c -fmessage-length=0 -fPIC -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/class.o: C:/workspace/knife/native/src/class.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -I"C:\workspace\knife\native\include\windows" -O2 -g -Wall -c -fmessage-length=0 -fPIC -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/field.o: C:/workspace/knife/native/src/field.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -I"C:\workspace\knife\native\include\windows" -O2 -g -Wall -c -fmessage-length=0 -fPIC -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/reference.o: C:/workspace/knife/native/src/reference.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -I"C:\workspace\knife\native\include\windows" -O2 -g -Wall -c -fmessage-length=0 -fPIC -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/trace.o: C:/workspace/knife/native/src/trace.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -I"C:\workspace\knife\native\include\windows" -O2 -g -Wall -c -fmessage-length=0 -fPIC -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '

src/util.o: C:/workspace/knife/native/src/util.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -I"C:\workspace\knife\native\include\windows" -O2 -g -Wall -c -fmessage-length=0 -fPIC -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


