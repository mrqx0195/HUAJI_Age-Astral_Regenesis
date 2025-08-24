package net.mrqx.huajiage.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

@SuppressWarnings("all")
public class HuaJiMathHelper {
    public static Vec3 getVectorEntity(Entity source, Entity target) {
        BlockPos eaterPos = source.blockPosition();
        BlockPos targetPos = target.blockPosition();

        return new Vec3(targetPos.getX() - eaterPos.getX(), targetPos.getY() - eaterPos.getY(),
                targetPos.getZ() - eaterPos.getZ()).normalize();
    }

    public static Vec3 getVectorEntityEye(Entity source, Entity target) {
        Vec3 eaterPos = source.getEyePosition(0);
        Vec3 targetPos = target.getEyePosition(0);
        return new Vec3(targetPos.x - eaterPos.x, targetPos.y - eaterPos.y, targetPos.z - eaterPos.z)
                .normalize();
    }

    public static Vec3 getVector(Vec3 sourcePos, Vec3 targetPos) {
        return new Vec3(targetPos.x - sourcePos.x, targetPos.y - sourcePos.y, targetPos.z - sourcePos.z)
                .normalize();
    }

    public static double getDistance(Vec3 sourcePos, Vec3 targetPos) {
        return new Vec3(targetPos.x - sourcePos.x, targetPos.y - sourcePos.y, targetPos.z - sourcePos.z)
                .length();
    }

    public static double getDistance(BlockPos sourcePos, BlockPos targetPos) {
        return new Vec3(targetPos.getX() - sourcePos.getX(),
                targetPos.getY() - sourcePos.getY(), targetPos.getZ() - sourcePos.getZ()).length();
    }

    public static float getAABBSize(AABB box) {
        float a = Math.abs((float) (box.maxX - box.minX));
        float b = Math.abs((float) (box.maxY - box.minY));
        float c = Math.abs((float) (box.maxZ - box.minZ));

        return a * b * c;
    }

    public static double getDegreeXZ(Vec3 v1, Vec3 v2) {
        Vec3 vec1 = v1.add(0, -v1.y, 0).normalize();
        Vec3 vec2 = v2.add(0, -v2.y, 0).normalize();
        double cos = (vec1.x * vec2.x + vec1.z * vec2.z) / (vec1.length() * vec2.length());

        return (double) Math.round(Math.toDegrees(Math.acos(cos)));
    }

    public static double getDegreeXY(Vec3 v1, Vec3 v2) {
        Vec3 vec1 = v1.add(0, 0, -v1.z).normalize();
        Vec3 vec2 = v2.add(0, 0, -v2.z).normalize();
        double cos = (vec1.x * vec2.x + vec1.y * vec2.y) / (vec1.length() * vec2.length());

        return (double) Math.round(Math.toDegrees(Math.acos(cos)));
    }

    public static double getDegreeZY(Vec3 v1, Vec3 v2) {
        Vec3 vec1 = v1.add(-v1.x, 0, 0).normalize();
        Vec3 vec2 = v2.add(-v2.x, 0, 0).normalize();
        double cos = (vec1.z * vec2.z + vec1.y * vec2.y) / (vec1.length() * vec2.length());

        return (double) Math.round(Math.toDegrees(Math.acos(cos)));
    }

    public static Vec3 getVecPlus(Vec3 v1, Vec3 v2, double l1, double l2) {

        return new Vec3(l1 * v1.x + l2 * v2.x, l1 * v1.y + l2 * v2.y, l1 * v1.z + l2 * v2.z);

    }

    public static Vec3 getVecPlus(Vec3 v1, Vec3 v2, Vec3 v3, double l1, double l2, double l3) {

        return new Vec3(l1 * v1.x + l2 * v2.x + l3 * v3.x, l1 * v1.y + l2 * v2.y + l3 * v3.y,
                l1 * v1.z + l2 * v2.z + l3 * v3.z);

    }

    public static Vec3 getVecCross(Vec3 v1, Vec3 v2) {

        return new Vec3(v1.y * v2.z - v1.z * v2.y,
                -v1.x * v2.z + v1.z * v2.x,
                v1.x * v2.y - v1.y * v2.x);
    }

    public static Vec3 getPositionRelative2D(Entity entity, float x, float z) {
        float yaw = entity.getYRot();
        if (entity instanceof LivingEntity living) {
            yaw = living.yBodyRot;
        }
        Vec3 forward = getVectorForRotation(0, yaw);
        Vec3 vertical = getVectorForRotation(0, yaw + 90);

        return getVecPlus(vertical, forward, x, z);

    }

    public static Vec3 getVectorForRotation(float pitch, float yaw) {
        double f = Math.cos(-yaw * 0.017453292F - (float) Math.PI);
        double f1 = Math.sin(-yaw * 0.017453292F - (float) Math.PI);
        double f2 = -Math.cos(-pitch * 0.017453292F);
        double f3 = Math.sin(-pitch * 0.017453292F);
        return new Vec3(f1 * f2, f3, f * f2);
    }
}
